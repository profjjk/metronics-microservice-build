import { useQuery } from 'react-query';
import axios from 'axios';

const fetchCustomer = async customerId => {
  const { data } = await axios.get(`http://localhost:8080/api/customers/${customerId}`);
  return data;
}

export default function useCustomer(customerId) {
  return useQuery(['customer', customerId], () => fetchCustomer(customerId));
}