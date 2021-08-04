import { useQuery } from 'react-query';
import { SideNavbar } from '../components';
import React from 'react';
import API from '../API';

const fetchJobs = async () => await API.getAllJobs();
const fetchCustomers = async () => await API.getAllCustomers();
const fetchParts = async () => await API.getAllParts();

const Home = () => {
  const jobs = useQuery('jobs', fetchJobs);
  const customers = useQuery('customers', fetchCustomers);
  const parts = useQuery('parts', fetchParts);
  console.log("*** JOBS ***");
  console.log(jobs);
  console.log("*** CUSTOMERS ***");
  console.log(customers);
  console.log("*** PARTS ***");
  console.log(parts);

  switch (jobs.status || parts.status || customers.status) {
    case 'loading':
      return <h1 className="text-center my-5">Loading</h1>;
    case 'error':
      return <h4 className="text-center my-5">Oops, something went wrong!</h4>
    default:
      return (
        <main>
          <SideNavbar />
          <h1 className="text-center my-5">Data Load Successful</h1>
        </main>
      )
  }
}

export default Home;