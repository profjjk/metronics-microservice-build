import { useQuery } from 'react-query';
import axios from 'axios';

const fetchParts = async () => {
  const parts = await axios.get('http://localhost:8080/api/parts/');
  return parts;
}

export default function useParts() {
  return useQuery('parts', () => fetchParts());
}