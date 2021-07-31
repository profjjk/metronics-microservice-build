import { useEffect, useState } from 'react';

const NewRequests = ({ requests }) => {
  const [newRequests, setNewRequests] = useState([]);

  useEffect(() => {
    let newList = [];
    for (let request of requests) {
      if (request.status === 'waiting') {
        newList.push(request);
      }
    }
    setNewRequests(newList);
  }, [])

  return (
    <div className="my-5">
      <h5><strong>New Service Requests</strong></h5>
      <table className="table">
        <thead>
          <tr>
            <th scope="col">Company Name</th>
            <th scope="col">Address</th>
            <th scope="col">Phone #</th>
            <th scope="col">Contact Name</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          {newRequests.map(req => (
            <tr key={req.id}>
              <td>{req.customer.businessName}</td>
              <td>
                {req.customer.street}<br></br>
                {req.customer.city}, {req.customer.state} {req.customer.zipcode}
              </td>
              <td>{req.customer.phone}</td>
              <td>{req.customer.contactName}</td>
              <td><button className="btn btn-primary">view</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default NewRequests;