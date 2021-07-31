import { useEffect, useState } from "react";
import { useQuery, useMutation, useQueryClient } from "react-query";
import API from '../API';

const fetchRequest = async (id) => await API.getRequestById(id);

const RequestForm = ({ selectedRequest, setShowForm }) => {
  // Fetch RequestViewModel by id from main-service API.
  const { data, status, error } = useQuery(
    'selectedRequest',
    () => fetchRequest(selectedRequest)
  );
    // Create empty request object in state.
    const [request, updateRequest] = useState({
      id: '', customerId: '', status: '', type: '', dateCompleted: '', invoiceNumber: '', problemNotes: '', repairNotes: ''
    });
    // Create empty customer object in state.
    const [customer, updateCustomer] = useState({
      id: '', businessName: '', contactName: '', phone: '', street: '', city: '', state: '', zipcode: ''
    });


  const queryClient = useQueryClient();
  const addRequest = useMutation(request => API.createRequest(request))

  const handleSubmit = e => {
    e.preventDefault();
    // addRequest.mutate(Object.fromEntries(new FormData(e.target)), {
    //   onSuccess: () => {
    //     queryClient.invalidateQueries('requests');
    //     console.log("Success!")
    //   }
    // })
    console.log(request, customer)
    setShowForm(false)
  }

  // Update request object with user input.
  const handleRequestChange = e => {
    updateRequest({
      ...request,
      [e.target.name]: e.target.value
    })
  }
  // Update customer object with user input.
  const handleCustomerChange = e => {
    updateCustomer({
      ...customer,
      [e.target.name]: e.target.value
    })
  }

  switch (status) {
    case 'loading':
      return <h4 className="text-center my-5">Loading</h4>
    case 'error':
      return <h4 className="text-center my-5">{error.message}</h4>
    default:
      return (
        <>
          <form className="p-5" onSubmit={handleSubmit} >
            <h1 className="text-primary text-center mb-5">Service Request Form</h1>
            <div id="dropdown-area" className="my-3">
              <div className="px-3">
                <h6>Status</h6>
                <select className="form-select" name="status" onChange={handleRequestChange}>
                  <option>{data.status}</option>
                  {data.status === 'waiting' ? '' : <option>waiting</option>}
                  {data.status === 'scheduled' ? '' : <option>scheduled</option>}
                  {data.status === 'completed' ? '' : <option>completed</option>}
                  {data.status === 'canceled' ? '' : <option>canceled</option>}
                </select>
              </div>      
              <div className="px-3">
                <h6>Type</h6>
                <select className="form-select" name="type" onChange={handleRequestChange}>
                  <option>{data.type}</option>
                  {data.type === 'maintenance' ? '' : <option>maintenance</option>}
                  {data.type === 'repair' ? '' : <option>repair</option>}
                  {data.type === 'callback' ? '' : <option>callback</option>}
                  {data.type === 'training' ? '' : <option>training</option>}
                </select>
              </div>
              <div className="px-3">
                <h6>Date Completed</h6>
                <input type="text" className="form-control"
                  name="dateCompleted" 
                  placeholder={data.dateCompleted} 
                  onChange={handleRequestChange} />
              </div>
              <div className="px-3">
                <h6>Invoice #</h6>
                <input type="text" className="form-control" 
                  name="invoiceNumber"
                  placeholder={data.invoiceNumber}
                  onChange={handleRequestChange} />
              </div>
            </div>
    
            <div id="customer-area" className="my-3">
              <div className="px-3">
                <h6>Contact Information</h6>
                <input className="form-control" placeholder={data.customer.businessName}
                  name="businessName" onChange={handleCustomerChange} />
                <input className="form-control my-2" placeholder={data.customer.contactName}
                  name="contactName" onChange={handleCustomerChange} />
                <input className="form-control" placeholder={data.customer.phone}
                  name="phone" onChange={handleCustomerChange} />
              </div>
              <div className="px-3">
                <h6>Address</h6>
                <input className="form-control" placeholder={data.customer.street}
                  name="street" onChange={handleCustomerChange} />
                <input className="form-control my-2" />
                <div id="address">
                  <input className="form-control" placeholder={data.customer.city}
                    name="city" onChange={handleCustomerChange} />
                  <input className="form-control" placeholder={data.customer.state}
                    name="state" onChange={handleCustomerChange} />
                  <input className="form-control" placeholder={data.customer.zipcode}
                    name="zipcode" onChange={handleCustomerChange} />
                </div>
              </div>
            </div>
    
            <div id="notes-area" className="my-3 px-3">
              <h6>Problem Description</h6>
              <textarea className="form-control" name="problemNotes" 
              placeholder={data.problemNotes} onChange={handleRequestChange}></textarea>
              <h6 className="mt-3">Repair Notes</h6>
              <textarea className="form-control" name="repairNotes" 
              placeholder={data.repairNotes} onChange={handleRequestChange}></textarea>
            </div>
    
            <div className="mt-4 px-3">
              <button className="btn btn-primary me-3 form-btn" type="submit">Save</button>
              <button className="btn btn-danger form-btn">Cancel</button>
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
      )
  }
}

export default RequestForm;


  // Create request object in state.
  // const [request, updateRequest] = useState({
  //   id: data.id, customerId: data.customer.id, status: data.status, type: data.type, dateCompleted: data.dateCompleted, 
  //   invoiceNumber: data.invoiceNumber, problemNotes: data.problemNotes, repairNotes: data.repairNotes
  // });
  // Create customer object in state.
  // const [customer, updateCustomer] = useState({
  //   id: data.customer.id, businessName: data.customer.businessName, contactName: data.customer.contactName, phone: data.customer.phone, 
  //   street: data.customer.street, city: data.customer.city, state: data.customer.state, zipcode: data.customer.zipcode
  // });