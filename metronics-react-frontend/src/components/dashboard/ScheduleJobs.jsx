import { useEffect, useState } from 'react';

const ScheduleJobs = ({ jobs }) => {
  const [jobList, setJobList] = useState(jobs);

  useEffect(() => {
    setJobList(jobs.filter(job => job.status === 'waiting'))
  }, [jobs])

  return (
    <div className="my-5">
      <h4 className="pt-5"><strong>Jobs that need to be scheduled</strong></h4>
      <table className="table">
        <thead>
          <tr>
            <th scope="col">Company Name</th>
            <th scope="col">Address</th>
            <th scope="col">Phone #</th>
            <th scope="col">Contact Name</th>
          </tr>
        </thead>
        <tbody>
          {jobList.map(job => (
            <tr key={job.id}>
              <td>{job.customer.businessName}</td>
              <td>
                {job.customer.street1}
                {job.customer.street2 !== "" ? ", " + job.customer.street2 : ""}
                <br></br>
                {job.customer.city}, {job.customer.state} {job.customer.zipcode}
              </td>
              <td>{job.customer.phone}</td>
              <td>{job.customer.contactName}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default ScheduleJobs;
