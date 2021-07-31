import { useState } from "react";
import { useHistory } from "react-router-dom";


const CustomersTable = ({ searchResults }) => {
//   console.log(customers);
 const history = useHistory();
 const [ customer, setCustomer ] = useState();

// const routeToView = () => {

// history.push({
//   pathname: "/view",
//   state: {searchResults: searchResults}
// });

// }
  return (
    <div id="requests-table" className="mt-5">
      <h3 className="float-start">Customer Search Results:</h3>
      {/* <div className="float-end">
          <select className="form-select">
            <option>Filter by status</option>
            <option>Waiting</option>
            <option>Scheduled</option>
            <option>Completed</option>
            <option>Canceled</option>
          </select>
        </div> */}
      <table className="table">
        <thead>
          <tr>
            <th scope="col">Company Name</th>
            <th scope="col">Contact</th>
            <th scope="col">Address</th>
            <th scope="col">Phone Number</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          {searchResults.map((c) => (
            <tr
            key={c.id}
            >
              <td className="text-center">{c.businessName}</td>
              <td className="text-center">
                {c.contactName ? c.contactName : "--"}
              </td>
              <td>
                {c.street} {c.city}, {c.state} {c.zipcode}
              </td>
              <td>{c.phone}</td>
              <td>
                <button className="btn btn-primary"
                onClick={() => {
                  history.push({
                    pathname: "/view/" + c.id,
                    state: {setCustomer: c.id}
                  });
                }}
                >view</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CustomersTable;