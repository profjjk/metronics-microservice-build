import { useQuery, useQueryClient } from 'react-query';
import { SideNavBar, QuickActions, PartsWarning, NewRequests } from '../components';
import React from 'react';
import API from '../API';

const fetchRequests = async () => await API.getAllRequests();
const fetchParts = async () => await API.getAllParts();
const fetchCustomers = async () => await API.getAllCustomers();

const HomePage = () => {
  const requests = useQuery('requests', fetchRequests);
  const parts = useQuery('parts', fetchParts);
  const customers = useQuery('customers', fetchCustomers);

  // const queryClient = useQueryClient();

  switch (requests.status || parts.status) {
    case 'loading':
      return <h1 className="text-center my-5">Loading</h1>;
    case 'error':
      return <h4 className="text-center my-5">Oops, something went wrong!</h4>
    default:
      return (
      <>
        <SideNavBar />
        <div className="main-section">
          <QuickActions />
          <PartsWarning 
            parts={parts.data} />
          <NewRequests 
            requests={requests.data} />
        </div>
      </>
    )
  }
}

export default HomePage;