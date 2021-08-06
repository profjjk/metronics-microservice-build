import { useState } from "react";
import { useMutation, useQueryClient } from "react-query";
import useJob from '../hooks/useJob';
import API from '../API';

const JobForm = ({ jobId, setShowForm }) => {
  const { status, data, error } = useJob(jobId);

  // Containers to hold job and customer info.
  const [job, updateJob] = useState(() => {
    if (status === 'success') {
      return {
        id: data.data.id,
        customerId: data.data.customerId,
        status: data.data.status,
        type: data.data.type,
        dateCompleted: data.data.dateCompleted,
        invoiceNumber: data.data.invoiceNumber,
        problemNotes: data.data.problemNotes,
        repairNotes: data.data.repairNotes,
      }
    }
    return {};
  });
  const [customer, updateCustomer] = useState(() => {
    if (status === 'success') {
      return {
        id: data.data.customer.id,
        businessName: data.data.customer.businessName,
        contactName: data.data.customer.contactName,
        phone: data.data.customer.phone,
        street1: data.data.customer.street1,
        street2: data.data.customer.street2,
        city: data.data.customer.city,
        state: data.data.customer.state,
        zipcode: data.data.customer.zipcode,
      }
    }
    return {};
  });

  // Mutations
  const queryClient = useQueryClient();
  const editJob = useMutation(job => API.updateJob(job), {
    onSuccess: () => {
      queryClient.invalidateQueries('jobs')
      queryClient.invalidateQueries(['job', jobId])
      console.log("Success!")
    }
  });

  // Handlers
  const handleSubmit = async e => {
    e.preventDefault();
    await editJob.mutate(Object.fromEntries(new FormData(e.target)))
    setShowForm(false);
  };
  const handleJobChange = e => {
    updateJob({
      ...job,
      [e.target.name]: e.target.value,
    });
  };
  const handleCustomerChange = e => {
    updateCustomer({
      ...customer,
      [e.target.name]: e.target.value,
    });
  };

  switch (status) {
    case "loading":
      return <h4 className="text-center my-5">Loading</h4>;
    case "error":
      return <h4 className="text-center my-5">Error: {error.message}</h4>;
    default:
      return (
        <>
          <form className="p-5" onSubmit={handleSubmit}>
            <h1 className="text-primary text-center mb-5">Service Job Form</h1>
            <div id="dropdown-area" className="my-3">
              <div className="px-3">
                <h6>Status</h6>
                <select
                  className="form-select"
                  name="status"
                  onChange={handleJobChange}
                >
                  <option>{data.data.status}</option>
                  {data.data.status === "waiting" ? "" : <option>waiting</option>}
                  {data.data.status === "scheduled" ? (
                    ""
                  ) : (
                    <option>scheduled</option>
                  )}
                  {data.data.status === "completed" ? (
                    ""
                  ) : (
                    <option>completed</option>
                  )}
                  {data.data.status === "canceled" ? "" : <option>canceled</option>}
                </select>
              </div>
              <div className="px-3">
                <h6>Type</h6>
                <select
                  className="form-select"
                  name="type"
                  onChange={handleJobChange}
                >
                  <option>{data.data.type}</option>
                  {data.data.type === "maintenance" ? (
                    ""
                  ) : (
                    <option>maintenance</option>
                  )}
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
                  onChange={handleJobChange}
                />
              </div>
              <div className="px-3">
                <h6>Invoice #</h6>
                <input
                  type="text"
                  className="form-control"
                  name="invoiceNumber"
                  defaultValue={data.data.invoiceNumber || ""}
                  onChange={handleJobChange}
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
                  onChange={handleCustomerChange}
                />
                <input
                  type="text"
                  className="form-control my-2"
                  name="contactName"
                  placeholder={"contact name"}
                  defaultValue={data.data.customer.contactName || ""}
                  onChange={handleCustomerChange}
                />
                <input
                  type="text"
                  className="form-control"
                  name="phone"
                  placeholder={"phone #"}
                  defaultValue={data.data.customer.phone || ""}
                  onChange={handleCustomerChange}
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
                  onChange={handleCustomerChange}
                />
                <input
                  type="text"
                  className="form-control my-2"
                  name="street2"
                  placeholder={"street 2"}
                  defaultValue={data.data.customer.street2 || ""}
                  onChange={handleCustomerChange}
                />
                <div id="address">
                  <input
                    type="text"
                    className="form-control"
                    name="city"
                    placeholder={"city"}
                    defaultValue={data.data.customer.city || ""}
                    onChange={handleCustomerChange}
                  />
                  <input
                    type="text"
                    className="form-control"
                    name="state"
                    placeholder={"state"}
                    defaultValue={data.data.customer.state || ""}
                    onChange={handleCustomerChange}
                  />
                  <input
                    type="text"
                    className="form-control"
                    name="zipcode"
                    placeholder={"zip code"}
                    defaultValue={data.data.customer.zipcode || ""}
                    onChange={handleCustomerChange}
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
                onChange={handleJobChange}
              ></textarea>
              <h6 className="mt-3">Repair Notes</h6>
              <textarea
                className="form-control"
                name="repairNotes"
                defaultValue={data.data.repairNotes || ""}
                onChange={handleJobChange}
              ></textarea>
            </div>

            <div className="mt-4 px-3">
              <button className="btn btn-primary me-3 form-btn" type="submit">
                Save
              </button>
              <button className="btn btn-danger form-btn"
                onClick={() => setShowForm(false)}
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

export default JobForm;
