import { useQueryClient } from 'react-query';
import { SideNavbar } from '../components';

const Customers = () => {
  const queryClient = useQueryClient();
  const customerList = queryClient.getQueryData('customers');
  console.log(customerList)

  return (
    <main>
      <SideNavbar />
      <h1 className="text-center my-5">Customers Page</h1>
    </main>
  )
}

export default Customers;