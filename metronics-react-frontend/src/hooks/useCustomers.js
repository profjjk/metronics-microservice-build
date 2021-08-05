import { useQuery } from 'react-query';
import axios from 'axios';

const fetchCustomers = async () => {
  const { data } = await axios.get('http://localhost:8080/api/customers/');
  return data;
}

export default function useCustomers() {
  return useQuery('customers', () => fetchCustomers);
}