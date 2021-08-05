import { useQuery } from 'react-query';
import axios from 'axios';

const fetchParts = async () => {
  const { data } = await axios.get('http://localhost:8080/api/parts/');
  return data;
}

export default function useParts() {
  return useQuery('parts', () => fetchParts);
}