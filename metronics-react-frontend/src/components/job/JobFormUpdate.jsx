import { useRef } from "react";
import { useMutation, useQueryClient } from "react-query";
import useJob from '../../hooks/useJob';
import API from '../../API';

const JobFormUpdate = ({ jobId, setJobId, setShowFormUpdate }) => {
  const { status, data, error } = useJob(jobId);

  // Capture form input for job
  let jobStatus = useRef(''); let type = useRef(''); let dateCompleted = useRef(''); 
  let invoiceNumber = useRef(''); let problemNotes = useRef(''); let repairNotes = useRef('');
  // Capture form input for customer
  let customerId; let businessName = useRef(''); let contactName = useRef(''); let phone = useRef('');
  let street1 = useRef(''); let street2 = useRef(''); let city = useRef(''); let state = useRef(''); let zipcode = useRef('');

  // Mutations
  const queryClient = useQueryClient();
  const editJob = useMutation(job => API.updateJob(job), {
    onSuccess: () => {
      queryClient.invalidateQueries('jobs')
      queryClient.invalidateQueries(['job', jobId])
      console.log("Job updated!")
    }
  });
  const editCustomer = useMutation(customer => API.updateCustomer(customer), {
    onSuccess: () => {
      queryClient.invalidateQueries('customers')
      queryClient.invalidateQueries(['customer', customerId])
      console.log("Customer updated!")
    }
  })

  // Handlers
  const handleSubmit = async e => {
    e.preventDefault();
    const jobInfo = {
      id: jobId, 
      customerId: customerId, 
      status: jobStatus.current.value, 
      type: type.current.value, 
      dateCompleted: dateCompleted.current.value, 
      invoiceNumber: invoiceNumber.current.value, 
      problemNotes: problemNotes.current.value, 
      repairNotes: repairNotes.current.value
    }
    const customerInfo = {
      id: customerId, 
      businessName: businessName.current.value, 
      contactName: contactName.current.value, 
      phone: phone.current.value, 
      street1: street1.current.value, 
      street2: street2.current.value,
      city: city.current.value, 
      state: state.current.value, 
      zipcode: zipcode.current.value
    }
    await editJob.mutate(jobInfo);
    await editCustomer.mutate(customerInfo)
    setJobId('');
    setShowFormUpdate(false);
  };

  switch (status) {
    case "loading":
      return <h4 className="text-center my-5">Loading</h4>;
    case "error":
      return <h4 className="text-center my-5">Error: {error.message}</h4>;
    default:
      customerId = data.data.customerId

      return (
        <>
          <form className="p-5" onSubmit={handleSubmit}>
            <h1 className="text-primary text-center mb-5">Service Job Form</h1>
            <div id="dropdown-area" className="my-3">
              <div className="px-3">
                <h6>Status</h6>
                <select className="form-select" name="status" ref={jobStatus}>
                  <option>{data.data.status}</option>
                  {data.data.status === "waiting" ? "" : <option>waiting</option>}
                  {data.data.status === "scheduled" ? "" : <option>scheduled</option>}
                  {data.data.status === "completed" ? "" : <option>completed</option>}
                  {data.data.status === "canceled" ? "" : <option>canceled</option>}
                </select>
              </div>
              <div className="px-3">
                <h6>Type</h6>
                <select className="form-select" name="type" ref={type}>
                  <option>{data.data.type}</option>
                  {data.data.type === "maintenance" ? "" : <option>maintenance</option>}
                  {data.data.type === "repair" ? "" : <option>repair</option>}
                  {data.data.type === "callback" ? "" : <option>callback</option>}
                  {data.data.type === "training" ? "" : <option>training</option>}
                </select>
              </div>
              <div className="px-3">
                <h6>Date Completed</h6>
                <input
                  type="text"
                  className="form-control"
                  name="dateCompleted"
                  defaultValue={data.data.dateCompleted || ""}
                  ref={dateCompleted}
                />
              </div>
              <div className="px-3">
                <h6>Invoice #</h6>
                <input
                  type="text"
                  className="form-control"
                  name="invoiceNumber"
                  defaultValue={data.data.invoiceNumber || ""}
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
                  defaultValue={data.data.customer.businessName || ""}
                  ref={businessName}
                />
                <input
                  type="text"
                  className="form-control my-2"
                  name="contactName"
                  placeholder={"contact name"}
                  defaultValue={data.data.customer.contactName || ""}
                  ref={contactName}
                />
                <input
                  type="text"
                  className="form-control"
                  name="phone"
                  placeholder={"phone #"}
                  defaultValue={data.data.customer.phone || ""}
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
                  defaultValue={data.data.customer.street1 || ""}
                  ref={street1}
                />
                <input
                  type="text"
                  className="form-control my-2"
                  name="street2"
                  placeholder={"street 2"}
                  defaultValue={data.data.customer.street2 || ""}
                  ref={street2}
                />
                <div id="address">
                  <input
                    type="text"
                    className="form-control"
                    name="city"
                    placeholder={"city"}
                    defaultValue={data.data.customer.city || ""}
                    ref={city}
                  />
                  <input
                    type="text"
                    className="form-control"
                    name="state"
                    placeholder={"state"}
                    defaultValue={data.data.customer.state || ""}
                    ref={state}
                  />
                  <input
                    type="text"
                    className="form-control"
                    name="zipcode"
                    placeholder={"zip code"}
                    defaultValue={data.data.customer.zipcode || ""}
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
                defaultValue={data.data.problemNotes || ""}
                ref={problemNotes}
              ></textarea>
              <h6 className="mt-3">Repair Notes</h6>
              <textarea
                className="form-control"
                name="repairNotes"
                defaultValue={data.data.repairNotes || ""}
                ref={repairNotes}
              ></textarea>
            </div>

            <div className="mt-4 px-3">
              <button className="btn btn-primary me-3 form-btn" type="submit">
                Save
              </button>
              <button className="btn btn-danger form-btn"
                onClick={() => setShowFormUpdate(false)}
                >Cancel
              </button>
            </div>
          </form>

          {/* <div id="parts-area" className="mt-5 p-5">
              <div>
                <h6 className="px-3">Add any parts that you used:</h6>
                <div className="part-form px-3">
                  <input className="form-control" placeholder="Part #" name="partNumber" />
                  <input className="form-control" type="number" placeholder="Quantity" name="partQuantity" />
                  <div><button className="btn btn-success">Add</button></div>
                </div>
              </div>
              <div>
                <h6>List of parts used:</h6>
                <table className="table">
                  <thead>
                    <tr>
                      <th>Part #</th>
                      <th>Description</th>
                      <th># Used</th>
                      <th></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>sdfasd</td>
                      <td>asfsd</td>
                      <td>asdfas</td>
                      <td><button className="btn btn-warning">X</button></td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div> */}
        </>
      );
  }
}

export default JobFormUpdate;
