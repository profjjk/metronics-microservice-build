import { useState } from "react";
import { useMutation, useQueryClient } from "react-query";
import API from '../../API';

const CustomerTable = ({ customers }) => {
  const [customerList, setCustomerList] = useState(customers)

  // Mutations
  const queryClient = useQueryClient();
  const deleteCustomer = useMutation(id => API.deleteCustomer(id), {
    onSuccess: () => {
      queryClient.invalidateQueries('customers')
      console.log("Customer deleted!")
    }
  })

  // Event Handlers
  const deleteHandler = async e => {
    e.preventDefault();
    await deleteCustomer.mutate(parseInt(e.target.dataset.id))
  }

  return (
    <div className="mt-5">
      <h3 className="float-start">Customer Search Results:</h3>
      <table className="table">
        <thead>
          <tr>
            <th scope="col">Business Name</th>
            <th scope="col">Phone #</th>
            <th scope="col">Address</th>
            <th scope="col">Contact Name</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          {customerList.map(customer => (
            <tr key={customer.id}>
              <td>{customer.businessName}</td>
              <td>{customer.phone}</td>
              <td>
                {customer.street1}
                {customer.street2 !== '' ? customer.street2 : ''}<br></br>
                {customer.city}, {customer.state} {customer.zipcode}
              </td>
              <td>{customer.contactName}</td>
              <td>
                <button
                  className="btn btn-primary"
                  data-id={customer.id}
                  // onClick={viewHandler}
                  >view
                </button>
                <button
                  className="btn btn-danger ms-4"
                  data-id={customer.id}
                  onClick={deleteHandler}
                  >X
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CustomerTable;