import { useRef } from "react";
import { useMutation, useQueryClient } from "react-query";
import useJob from '../../hooks/useJob';
import API from '../../API';

const CustomerJobEdit = ({ jobId, setShowTable, customerId }) => {
  const { status, data, error } = useJob(jobId);

  // Capture form input for job
  let jobStatus = useRef(''); let type = useRef(''); let dateCompleted = useRef(''); 
  let invoiceNumber = useRef(''); let problemNotes = useRef(''); let repairNotes = useRef('');

  // Mutations
  const queryClient = useQueryClient();
  const editJob = useMutation(job => API.updateJob(job), {
    onSuccess: () => {
      queryClient.invalidateQueries('jobs')
      queryClient.invalidateQueries(['job', jobId])
      queryClient.invalidateQueries('customers')
      queryClient.invalidateQueries(['customer', customerId])
      console.log("Job updated!")
    }
  });

  // Handlers
  const handleSubmit = async e => {
    e.preventDefault();
    const jobInfo = {
      id: jobId, 
      customerId: customerId, 
      status: jobStatus.current.value, 
      type: type.current.value, 
      dateCompleted: dateCompleted.current.value, 
      invoiceNumber: invoiceNumber.current.value, 
      problemNotes: problemNotes.current.value, 
      repairNotes: repairNotes.current.value
    }
    await editJob.mutate(jobInfo);
    setShowTable(true);
  };

  switch (status) {
    case "loading":
      return <h4 className="text-center my-5">Loading</h4>;
    case "error":
      return <h4 className="text-center my-5">Error: {error.message}</h4>;
    default:
      return (
        <>
          <form className="p-5" onSubmit={handleSubmit}>
            <h4>Service Job Info:</h4>
            <div id="dropdown-area" className="my-3">
              <div className="px-3">
                <h6>Status</h6>
                <select className="form-select" name="status" ref={jobStatus}>
                  <option>{data.data.status}</option>
                  {data.data.status === "waiting" ? "" : <option>waiting</option>}
                  {data.data.status === "scheduled" ? "" : <option>scheduled</option>}
                  {data.data.status === "completed" ? "" : <option>completed</option>}
                  {data.data.status === "canceled" ? "" : <option>canceled</option>}
                </select>
              </div>
              <div className="px-3">
                <h6>Type</h6>
                <select className="form-select" name="type" ref={type}>
                  <option>{data.data.type}</option>
                  {data.data.type === "maintenance" ? "" : <option>maintenance</option>}
                  {data.data.type === "repair" ? "" : <option>repair</option>}
                  {data.data.type === "callback" ? "" : <option>callback</option>}
                  {data.data.type === "training" ? "" : <option>training</option>}
                </select>
              </div>
              <div className="px-3">
                <h6>Date Completed</h6>
                <input
                  type="text"
                  className="form-control"
                  name="dateCompleted"
                  defaultValue={data.data.dateCompleted || ""}
                  ref={dateCompleted}
                />
              </div>
              <div className="px-3">
                <h6>Invoice #</h6>
                <input
                  type="text"
                  className="form-control"
                  name="invoiceNumber"
                  defaultValue={data.data.invoiceNumber || ""}
                  ref={invoiceNumber}
                />
              </div>
            </div>

            <div id="notes-area" className="my-3 px-3">
              <h6>Problem Description</h6>
              <textarea
                className="form-control"
                name="problemNotes"
                defaultValue={data.data.problemNotes || ""}
                ref={problemNotes}
              ></textarea>
              <h6 className="mt-3">Repair Notes</h6>
              <textarea
                className="form-control"
                name="repairNotes"
                defaultValue={data.data.repairNotes || ""}
                ref={repairNotes}
              ></textarea>
            </div>

            <div className="mt-4 px-3">
              <button className="btn btn-primary me-3 form-btn" 
                type="submit"
                >Update
              </button>
              <button className="btn btn-secondary form-btn"
                onClick={() => setShowTable(true)}
                >Cancel
              </button>
            </div>
          </form>
        </>
      );
  }
}

export default CustomerJobEdit;
