import { useState, useEffect } from "react";
import { useMutation, useQueryClient } from "react-query";
import API from '../../API';

const CustomerTable = ({ setShowFormUpdate, setCustomerId, searchTerm, customers }) => {
  const [customerList, setCustomerList] = useState(customers);

  // Search for customers
  useEffect(() => {
    if (searchTerm === "") {
      setCustomerList(customers);
      return;
    }
    setCustomerList(
      customers.filter((customer) => {
        if (customer.businessName.toLowerCase().includes(searchTerm.toLowerCase())) {
          return true;
        } else if (customer.city !== null && customer.city.toLowerCase().includes(searchTerm.toLowerCase())) {
          return true;
        } else if (customer.phone !== null && customer.phone.includes(searchTerm)) {
          return true;
        }
        return false;
      })
    );
  }, [searchTerm, customers]);

  // Mutations
  const queryClient = useQueryClient();
  const deleteCustomer = useMutation((id) => API.deleteCustomer(id), {
    onSuccess: () => {
      queryClient.invalidateQueries("customers");
      console.log("Customer deleted!");
    },
  });

  // Event Handlers
  const viewHandler = e => {
    e.preventDefault();
    setCustomerId(parseInt(e.target.dataset.id));
    setShowFormUpdate(true);
  };
  const deleteHandler = async (e) => {
    e.preventDefault();
    await deleteCustomer.mutate(parseInt(e.target.dataset.id));
  };

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
          {customerList.map((customer) => (
            <tr key={customer.id}>
              <td>{customer.businessName}</td>
              <td>{customer.phone}</td>
              <td>
                {customer.street1}
                {customer.street2 !== "" ? ", " + customer.street2 : ""}
                <br></br>
                {customer.city}, {customer.state} {customer.zipcode}
              </td>
              <td>{customer.contactName}</td>
              <td>
                <div className="float-end">
                  <button
                    className="btn btn-warning"
                    data-id={customer.id}
                    onClick={viewHandler}
                  >
                    view
                  </button>
                  <button
                    className="btn btn-danger ms-4"
                    data-id={customer.id}
                    onClick={deleteHandler}
                  >
                    X
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CustomerTable;