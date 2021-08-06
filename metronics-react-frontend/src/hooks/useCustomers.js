import { useQuery } from 'react-query';
import axios from 'axios';

const fetchCustomers = async () => {
  const customers = await axios.get('http://localhost:5557/api/customers/');
  return customers;
}

export default function useCustomers() {
  return useQuery('customers', () => fetchCustomers());
}