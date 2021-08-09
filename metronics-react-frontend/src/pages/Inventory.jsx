import { useState } from 'react';
import useParts from '../hooks/useParts';
import { SideNavbar, Searchbar, PartsTable } from '../components';

const Inventory = () => {
  const { status, data, error, isFetching } = useParts();
  const [searchTerm, setSearchTerm] = useState('');

  switch (status) {
    case "loading":
      return <h1 className="text-center my-5">Loading</h1>;
    case "error":
      return <h4 className="text-center my-5">Error: {error.message}</h4>;
    default:
      console.log(data.data)
      return (
        <main>
          <SideNavbar />
          <div className="p-5">
            <Searchbar
              heading="Inventory Search"
              subheading="Search by part # or description"
              placeholder="Part # or description"
              setSearch={setSearchTerm}
            />
            <PartsTable 
              parts={data.data}
              searchTerm={searchTerm}
            />
            {isFetching ? (
              <p className="text-center my-5">
                Getting information from database...
              </p>
            ) : ( "" )}
          </div>
        </main>
      );
  }
}

export default Inventory;