import { useQueryClient } from 'react-query';
import { SideNavbar } from '../components';

const Jobs = () => {
  const queryClient = useQueryClient();
  const jobsList = queryClient.getQueryData('jobs');
  console.log(jobsList)

  return (
    <main>
      <SideNavbar />
      <h1 className="text-center my-5">Service Jobs Page</h1>
    </main>
  )
}

export default Jobs;