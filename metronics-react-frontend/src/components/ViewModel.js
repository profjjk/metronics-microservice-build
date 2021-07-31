import { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";
import API from "../API";
import { SideNavBar } from "../components";
function ViewModel() {
  const [requests, setRequests] = useState([]);
  const [customer, setCustomer] = useState([]);
  const filterRequests = (reqs) => {
    return reqs.filter((v) => { return v.customer.id === parseInt(id) });
  };
  const { id } = useParams()
  useEffect(() => {
    API.getAllRequests().then(async (res) => {
      await setRequests(filterRequests(res));
      console.log(requests)
    });
    console.log(id);
    API.getCustomerById(id).then((res) => {
      setCustomer(res);
    });
  }, []);
  return (
    <>
      <SideNavBar />
      <div className="main-section">
           {
        (customer != undefined)
          ?
          <div className="container">
            <div className="row">
              <div className="col">
                <h3>{customer.businessName}</h3>
              </div>
            </div>
            <div className="row">
              <div className="col">
                <div>
                  {customer.street}, {customer.city}{" "}
                </div>
                <div>
                  {customer.state}, {customer.zipcode}
                </div>
              </div>
              <div className="col">
                <strong>Phone</strong> <div>{customer.phone}</div>
              </div>
              <div className="col">
                <strong>Contact Name</strong>
                <div>{customer.contactName}</div>
              </div>
            </div>
            <table className="table">
              <thead>
                <tr>
                  <th scope="col">Invoice #</th>
                  <th scope="col">Date Completed</th>
                  <th scope="col">Type</th>
                  <th scope="col">Company Name</th>
                  <th scope="col">City</th>
                  <th scope="col">Status</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                {requests.map(req => (
                  <tr key={req.id}>
                    <td className="text-center">{req.invoiceNumber ? req.invoiceNumber : "--"}</td>
                    <td className="text-center">{req.dateCompleted ? req.dateCompleted : "--"}</td>
                    <td>{req.type}</td>
                    <td>{req.customer.businessName}</td>
                    <td>{req.customer.city}</td>
                    <td>{req.status}</td>
                    <td><button className="btn btn-primary">view</button></td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          :
          <div>Can't find anything</div>
      }
         
         </div>
    </>
  );
}
export default ViewModel;
