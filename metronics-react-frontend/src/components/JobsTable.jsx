import { useEffect, useState } from 'react';

const JobsTable = ({ setShowForm, setJobId, searchTerm, jobs }) => {
  const [jobList, setJobList] = useState(jobs)
  const [jobFilter, setJobFilter] = useState(jobList);
  const [jobStatus, setJobStatus] = useState("");


  useEffect(() => {
    if (jobStatus === "Waiting") {
      setJobFilter(jobList.filter((job) => job.status === "waiting"));
    } else if (jobStatus === "Scheduled") {
      setJobFilter(jobList.filter((job) => job.status === "scheduled"));
    } else if (jobStatus === "Completed") {
      setJobFilter(jobList.filter((job) => job.status === "completed"));
    } else if (jobStatus === "Canceled") {
      setJobFilter(jobList.filter((job) => job.status === "canceled"));
    } else {
      setJobFilter(jobList);
    }
  }, [jobStatus]);

  useEffect(() => {
    if (searchTerm === '') {
      setJobList(jobs);
      return;
    }
    const searchResults = [];
    for (let job of jobList) {
      let name = job.customer.businessName.toLowerCase(); let invoice = job.invoiceNumber; let date = job.dateCompleted;
      if (name.indexOf(searchTerm) !== -1 || invoice.indexOf(searchTerm) !== -1 || date.indexOf(searchTerm) !== -1) {
        searchResults.push(job);
      }
    }
    setJobList(searchResults);
  }, [searchTerm]);

  const selectionHandler = (e) => {
    e.preventDefault();
    setJobStatus(e.target.value);
  };

  const clickHandler = (e) => {
    e.preventDefault();
    setJobId(parseInt(e.target.dataset.id));
    setShowForm(true);
  };

  return (
    <div className="mt-5">
      <h3 className="float-start">Service Job Search Results:</h3>
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
          {jobFilter.map(job => (
            <tr key={job.id}>
              <td className="text-center">
                {job.invoiceNumber ? job.invoiceNumber : "--"}
              </td>
              <td className="text-center">
                {job.dateCompleted ? job.dateCompleted : "--"}
              </td>
              <td>{job.customer.businessName}</td>
              <td>{job.customer.city}</td>
              <td>{job.type}</td>
              <td>{job.status}</td>
              <td>
                <button
                  className="btn btn-primary"
                  data-id={job.id}
                  onClick={clickHandler}
                  >view
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default JobsTable;
