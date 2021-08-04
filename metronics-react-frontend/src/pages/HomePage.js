import { useQuery } from 'react-query';
import React from 'react';
import API from '../API';

const fetchJobs = async () => await API.getAllJobs();
const fetchParts = async () => await API.getAllParts();
const fetchCustomers = async () => await API.getAllCustomers();

const HomePage = () => {
  const jobs = useQuery('jobs', fetchJobs);
  const parts = useQuery('parts', fetchParts);
  const customers = useQuery('customers', fetchCustomers);

  switch (jobs.status || parts.status || customers.status) {
    case 'loading':
      return <h1 className="text-center my-5">Loading</h1>;
    case 'error':
      return <h4 className="text-center my-5">Oops, something went wrong!</h4>
    default:
      return <h1 className="text-center my-5">Data Load Successful</h1>;
  }
}

export default HomePage;