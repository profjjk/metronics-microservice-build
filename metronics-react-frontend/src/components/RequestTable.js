import { useEffect, useState } from 'react';

const RequestTable = ({ searchResults, setShowForm, setSelectedRequest }) => {
  const [filteredsearchResults, setFilteressearchResults] = useState(searchResults);
  const [status, setStatus] = useState('');

  useEffect(() => {
    if (status === 'Waiting') {
      setFilteressearchResults(searchResults.filter(req => req.status === 'waiting'));
    } else if (status === 'Scheduled') {
      setFilteressearchResults(searchResults.filter(req => req.status === 'scheduled'));
    } else if (status === 'Completed') {
      setFilteressearchResults(searchResults.filter(req => req.status === 'completed'));
    } else if (status === 'Canceled') {
      setFilteressearchResults(searchResults.filter(req => req.status === 'canceled'));
    } else {
      setFilteressearchResults(searchResults);
    }
  }, [status, searchResults])

  const selectionHandler = e => {
    e.preventDefault();
    setStatus(e.target.value);
  }

  const clickHandler = e => {
    e.preventDefault();
    setSelectedRequest(parseInt(e.target.dataset.id))
    setShowForm(true);
  }

  return (
    <div id="request-table" className="mt-5">
      <h3 className="float-start">Service Search Results:</h3>
      <div className="float-end">
        <select className="form-select" onChange={selectionHandler}>
          <option>Filter by status</option>
          <option>Waiting</option>
          <option>Scheduled</option>
          <option>Completed</option>
          <option>Canceled</option>
        </select>
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
          {filteredsearchResults.map(req => (
            <tr key={req.id}>
              <td className="text-center">{req.invoiceNumber ? req.invoiceNumber : "--"}</td>
              <td className="text-center">{req.dateCompleted ? req.dateCompleted : "--"}</td>
              <td>{req.type}</td>
              <td>{req.customer.businessName}</td>
              <td>{req.customer.city}</td>
              <td>{req.status}</td>
              <td>
                <button className="btn btn-primary" data-id={req.id}
                  onClick={clickHandler}>view</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default RequestTable;