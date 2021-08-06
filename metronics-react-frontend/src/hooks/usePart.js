import { useQuery } from 'react-query';
import axios from 'axios';

const fetchPart = async partId => {
  const part = await axios.get(`http://localhost:5557/api/parts/${partId}`);
  return part;
}

export default function usePart(partId) {
  return useQuery(['part', partId], () => fetchPart(partId));
}