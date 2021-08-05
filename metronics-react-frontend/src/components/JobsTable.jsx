import { useEffect, useState } from 'react';
import useJobs from '../hooks/useJobs';

const JobsTable = ({ setShowForm, setJobId }) => {
  const { status, data, error, isFetching } = useJobs();
  const [jobSearch, setJobSearch] = useState(data);
  const [jobFilter, setJobFilter] = useState(jobSearch);
  const [jobStatus, setJobStatus] = useState('');

  useEffect(() => {
    if (jobStatus === 'Waiting') {
      setJobFilter(jobSearch.filter(job => job.status === 'waiting'));
    } else if (jobStatus === 'Scheduled') {
      setJobFilter(jobSearch.filter(job => job.status === 'scheduled'));
    } else if (jobStatus === 'Completed') {
      setJobFilter(jobSearch.filter(job => job.status === 'completed'));
    } else if (jobStatus === 'Canceled') {
      setJobFilter(jobSearch.filter(job => job.status === 'canceled'));
    } else {
      setJobFilter(jobSearch);
    }
  }, [jobStatus, jobSearch])

  // useEffect(() => {
  //   if (jobSearch === '') {
  //     setJobSearch(data);
  //     return;
  //   }
  //   const searchJobsList = [];
  //   for (let job of data) {
  //     let name = job.customer.businessName.toLowerCase(); let invoice = job.invoiceNumber; let date = job.dateCompleted;
  //     if (name.indexOf(jobSearch) !== -1 || invoice.indexOf(jobSearch) !== -1 || date.indexOf(jobSearch) !== -1) {
  //       searchJobsList.push(job);
  //     }
  //   }
  //   setJobSearch(searchJobsList);
  // }, [jobSearch, data]);

  const selectionHandler = e => {
    e.preventDefault();
    setJobStatus(e.target.value);
  }

  const clickHandler = e => {
    e.preventDefault();
    setJobId(parseInt(e.target.dataset.id));
    setShowForm(true);
  }

  console.log(jobFilter)

  return (
    <div className="mt-5">
      {status === "loading" ? (
        <h3 className="text-center my-5">Loading...</h3>
      ) : status === "error" ? (
        <h3 className="text-center my-5">Error: {error.message}</h3>
      ) : (
        <>
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
              {jobFilter.map((job) => (
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
          <p className="text-center my-5">{isFetching ? "Background updating..." : ""}</p>
        </>
      )}
    </div>
  );
}

export default JobsTable;