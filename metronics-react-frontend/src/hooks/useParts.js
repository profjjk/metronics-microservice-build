import { useQuery } from 'react-query';
import axios from 'axios';

const fetchParts = async () => {
  const parts = await axios.get('http://localhost:5557/api/parts/');
  return parts;
}

export default function useParts() {
  return useQuery('parts', () => fetchParts());
}