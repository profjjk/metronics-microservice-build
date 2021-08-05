import { SideNavbar } from '../components';
import useJobs from '../hooks/useJobs';
import useCustomers from '../hooks/useCustomers';
import useParts from '../hooks/useParts';
import React from 'react';

const Home = () => {
  const jobs = useJobs();
  const customers = useCustomers();
  const parts = useParts();
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