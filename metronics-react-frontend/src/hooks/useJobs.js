import { useQuery } from 'react-query';
import axios from 'axios';

const fetchJobs = async () => {
  const jobs = await axios.get('http://localhost:8080/api/jobs/');
  return jobs;
}

export default function useJobs() {
  return useQuery('jobs', () => fetchJobs());
}