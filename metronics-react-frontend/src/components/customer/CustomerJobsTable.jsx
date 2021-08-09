import { useMutation, useQueryClient } from 'react-query';
import useJobs from '../../hooks/useJobs';
import API from '../../API';

const CustomerJobsTable = ({ customerId }) => {
  const { status, data, error } = useJobs();

  // Mutations
  const queryClient = useQueryClient();
  const deleteJob = useMutation((id) => API.deleteJob(id), {
    onSuccess: () => {
      queryClient.invalidateQueries("jobs");
      console.log("Job deleted!");
    },
  });

  // Handlers
  const viewHandler = (e) => {
    e.preventDefault();
    // setJobId(parseInt(e.target.dataset.id));
    // setShowFormUpdate(true);
  };
  const deleteHandler = async (e) => {
    e.preventDefault();
    await deleteJob.mutate(parseInt(e.target.dataset.id));
  };

  switch (status) {
    case "loading":
      return <h1 className="text-center my-5">Loading</h1>;
    case "error":
      return <h4 className="text-center my-5">Error: {error.message}</h4>;
    default:
      const jobs = data.data.filter(job => job.customerId === customerId)

      return (
        <div className="mt-5">
          <h3 className="float-start">Service History:</h3>
          <table className="table">
            <thead>
              <tr>
                <th scope="col" className="text-center">Invoice #</th>
                <th scope="col" className="text-center">Date Completed</th>
                <th scope="col">Service Notes</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              {jobs.map((job) => (
                <tr key={job.id}>
                  <td className="text-center">
                    {job.invoiceNumber ? job.invoiceNumber : "--"}
                  </td>
                  <td className="text-center">
                    {job.dateCompleted ? job.dateCompleted : "--"}
                  </td>
                  <td>{job.repairNotes !== '' ? job.repairNotes : "No notes to display..."}</td>
                  <td>
                    <button
                      className="btn btn-warning"
                      data-id={job.id}
                      onClick={viewHandler}
                    >
                      view
                    </button>
                    <button
                      className="btn btn-danger ms-4"
                      data-id={job.id}
                      onClick={deleteHandler}
                    >
                      X
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      );
  }
};

export default CustomerJobsTable;
