import { useQueryClient } from 'react-query';
import { SideNavbar } from '../components';

const Inventory = () => {
  const queryClient = useQueryClient();
  const partsList = queryClient.getQueryData('parts');
  console.log(partsList)

  return (
    <main>
      <SideNavbar />
      <h1 className="text-center my-5">Inventory Page</h1>
    </main>
  )
}

export default Inventory;