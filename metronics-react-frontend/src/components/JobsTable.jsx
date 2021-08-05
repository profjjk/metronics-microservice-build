import { useEffect, useState } from 'react';

const JobsTable = ({ setShowForm, setJobId, searchTerm, jobs }) => {
  const [jobList, setJobList] = useState(jobs)
  const [jobStatus, setJobStatus] = useState("");

  // Filter by status
  useEffect(() => {
    if (jobStatus === "Waiting") {
      setJobList(jobList.filter(job => job.status === "waiting"));
    } else if (jobStatus === "Scheduled") {
      setJobList(jobList.filter(job => job.status === "scheduled"));
    } else if (jobStatus === "Completed") {
      setJobList(jobList.filter(job => job.status === "completed"));
    } else if (jobStatus === "Canceled") {
      setJobList(jobList.filter(job => job.status === "canceled"));
    } else {
      setJobList(jobs);
    }
  }, [jobStatus]);

  // Search for jobs
  useEffect(() => {
    if (searchTerm === '') {
      setJobList(jobs);
      return;
    }
    setJobList(jobs.filter(job => {
      if (job.customer.businessName.toLowerCase().includes(searchTerm.toLowerCase())) {
        return true;
      } else if (job.dateCompleted !== null && job.dateCompleted.includes(searchTerm)) {
        return true;
      } else if (job.invoiceNumber !== null && job.invoiceNumber.includes(searchTerm)) {
        return true;
      } else {
        return false;
      }
    }))
  }, [searchTerm]);

  // Handlers
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
            <th scope="col">Company Name</th>
            <th scope="col">City</th>
            <th scope="col">Type</th>
            <th scope="col">Status</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          {jobList.map(job => (
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