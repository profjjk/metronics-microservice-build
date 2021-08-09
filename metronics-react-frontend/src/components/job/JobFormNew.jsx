import { useEffect, useRef, useState } from "react";
import { useMutation, useQueryClient } from "react-query";
import useCustomers from '../../hooks/useCustomers';
import AutoCompleteSearch from "./AutoCompleteSearch";
import API from '../../API';
import axios from "axios";

const JobFormNew = ({ setShowFormNew }) => {
  const { status, data, error } = useCustomers();
  const [customer, setCustomer] = useState();
  const [searchTerm, setSearchTerm] = useState('');
  const [customerExists, setCustomerExists] = useState(false);

  // Capture form input for job
  let jobStatus = useRef(''); let type = useRef(''); let dateCompleted = useRef(''); 
  let invoiceNumber = useRef(''); let problemNotes = useRef(''); let repairNotes = useRef('');
  // Capture form input for customer
  let customerId; let businessName = useRef(''); let contactName = useRef(''); let phone = useRef('');
  let street1 = useRef(''); let street2 = useRef(''); let city = useRef(''); let state = useRef(''); let zipcode = useRef('');

  // Find existing customer info
  useEffect(() => {
    if (searchTerm === '') {
      return;
    }
    setCustomer(data.data.filter(customer => customer.businessName.toLowerCase().includes(searchTerm.toLowerCase())));
  }, [searchTerm])

  // Mutations
  const queryClient = useQueryClient();
  const createJob = useMutation(job => API.createJob(job), {
    onSuccess: () => {
      queryClient.invalidateQueries('jobs')
      console.log("Job created!")
    }
  });
  const editCustomer = useMutation(customer => API.updateCustomer(customer), {
    onSuccess: () => {
      queryClient.invalidateQueries('customers')
      queryClient.invalidateQueries(['customer', customerId])
      console.log("Customer updated!")
    }
  })
  const createCustomer = useMutation(customer => API.createCustomer(customer), {
    onSuccess: () => {
      queryClient.invalidateQueries('customers')
      console.log("Customer created!")
    }
  });

  // Handlers
  const handleSubmit = async e => {
    e.preventDefault();
    const jobInfo = {
      status: jobStatus.current.value, 
      type: type.current.value, 
      dateCompleted: dateCompleted.current.value, 
      invoiceNumber: invoiceNumber.current.value, 
      problemNotes: problemNotes.current.value, 
      repairNotes: repairNotes.current.value
    }
    const customerInfo = {
      businessName: businessName.current.value, 
      contactName: contactName.current.value, 
      phone: phone.current.value, 
      street1: street1.current.value, 
      street2: street2.current.value,
      city: city.current.value, 
      state: state.current.value, 
      zipcode: zipcode.current.value
    }
    if (customerExists) {
      await editCustomer.mutate({ id: customer[0].id, ...customerInfo });
      await createJob.mutate({ customerId: customer[0].id, ...jobInfo });
    } else {
      let newCustomer = await createCustomer.mutateAsync(customerInfo)
      await createJob.mutate({ customerId: newCustomer.id, ...jobInfo })
    }
    setShowFormNew(false);
  };

  switch (status) {
    case "loading":
      return <h4 className="text-center my-5">Loading</h4>;
    case "error":
      return <h4 className="text-center my-5">Error: {error.message}</h4>;
    default:
      if (customer) {
        customerId = customer[0].id;
      }
      return (
        <>
          <AutoCompleteSearch 
            customers={data.data}
            setCustomer={setCustomer}
            setCustomerExists={setCustomerExists}
          />

          <form className="p-5" onSubmit={handleSubmit}>
            <h1 className="text-primary text-center mb-5">Service Job Form</h1>
            <div id="dropdown-area" className="my-3">
              <div className="px-3">
                <h6>Status</h6>
                <select className="form-select" name="status" ref={jobStatus}>
                  <option>waiting</option>}<option>scheduled</option>}
                  <option>completed</option>}<option>canceled</option>}
                </select>
              </div>
              <div className="px-3">
                <h6>Type</h6>
                <select className="form-select" name="type" ref={type}>
                  <option>maintenance</option>}<option>repair</option>}
                  <option>callback</option>}<option>training</option>}
                </select>
              </div>
              <div className="px-3">
                <h6>Date Completed</h6>
                <input
                  type="text"
                  className="form-control"
                  name="dateCompleted"
                  ref={dateCompleted}
                />
              </div>
              <div className="px-3">
                <h6>Invoice #</h6>
                <input
                  type="text"
                  className="form-control"
                  name="invoiceNumber"
                  ref={invoiceNumber}
                />
              </div>
            </div>

            <div id="customer-area" className="my-3">
              <div className="px-3">
                <h6>Contact Information</h6>
                <input
                  type="text"
                  className="form-control"
                  name="businessName"
                  placeholder={"business name"}
                  defaultValue={customer ? customer[0].businessName : ""}
                  ref={businessName}
                />
                <input
                  type="text"
                  className="form-control my-2"
                  name="contactName"
                  placeholder={"contact name"}
                  defaultValue={customer ? customer[0].contactName : ""}
                  ref={contactName}
                />
                <input
                  type="text"
                  className="form-control"
                  name="phone"
                  placeholder={"phone #"}
                  defaultValue={customer ? customer[0].phone : ""}
                  ref={phone}
                />
              </div>
              <div className="px-3">
                <h6>Address</h6>
                <input
                  type="text"
                  className="form-control"
                  name="street1"
                  placeholder={"street 1"}
                  defaultValue={customer ? customer[0].street1 : ""}
                  ref={street1}
                />
                <input
                  type="text"
                  className="form-control my-2"
                  name="street2"
                  placeholder={"street 2"}
                  defaultValue={customer ? customer[0].street2 : ""}
                  ref={street2}
                />
                <div id="address">
                  <input
                    type="text"
                    className="form-control"
                    name="city"
                    placeholder={"city"}
                    defaultValue={customer ? customer[0].city : ""}
                    ref={city}
                  />
                  <input
                    type="text"
                    className="form-control"
                    name="state"
                    placeholder={"state"}
                    defaultValue={customer ? customer[0].state : ""}
                    ref={state}
                  />
                  <input
                    type="text"
                    className="form-control"
                    name="zipcode"
                    placeholder={"zip code"}
                    defaultValue={customer ? customer[0].zipcode : ""}
                    ref={zipcode}
                  />
                </div>
              </div>
            </div>

            <div id="notes-area" className="my-3 px-3">
              <h6>Problem Description</h6>
              <textarea
                className="form-control"
                name="problemNotes"
                ref={problemNotes}
              ></textarea>
              <h6 className="mt-3">Repair Notes</h6>
              <textarea
                className="form-control"
                name="repairNotes"
                ref={repairNotes}
              ></textarea>
            </div>

            <div className="mt-4 px-3">
              <button className="btn btn-primary me-3 form-btn" type="submit">
                Save
              </button>
              <button
                className="btn btn-danger form-btn"
                onClick={() => setShowFormNew(false)}
              >
                Cancel
              </button>
            </div>
          </form>
        </>
      );
  }
}

export default JobFormNew;
