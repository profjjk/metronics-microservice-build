import { useQuery } from 'react-query';
import axios from 'axios';

const fetchJobs = async () => {
  const { data } = await axios.get(
    'http://localhost:8080/api/jobs/'
  );
  return data;
}

export default function useJobs() {
  return useQuery('jobs', () => fetchJobs);
}