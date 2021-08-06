import { useQuery } from 'react-query';
import axios from 'axios';

const fetchJob = async jobId => {
  const job = await axios.get(`http://localhost:5557/api/jobs/${jobId}`);
  return job;
}

export default function useJob(jobId) {
  return useQuery(['job', jobId], () => fetchJob(jobId));
}