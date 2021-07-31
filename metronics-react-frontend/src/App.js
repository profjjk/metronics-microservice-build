import "./App.css";
import {
  HomePage,
  InventoryPage,
  LoginForm,
  Customers,
  RequestPage,
  ViewModel,
  InventoryAddPart,
  AddCustomer
} from "./pages";
import { ReactQueryDevtools } from "react-query/devtools";
import { QueryClient, QueryClientProvider } from "react-query";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

const queryClient = new QueryClient();

const App = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <Router>
        <Switch>
          <Route exact path="/service" component={RequestPage} />
          <Route exact path="/home" component={HomePage} />
          <Route exact path="/inventory" component={InventoryPage} />
          <Route exact path="/customers" component={Customers} />
          <Route exact path="/customers/addcustomer" component={AddCustomer} />
          <Route exact path="/" component={LoginForm} />
          <Route exact path="/view/:id" component={ViewModel} />
          <Route exact path="/inventory/addpart" component={InventoryAddPart}/>
        </Switch>
      </Router>
      {/* <ReactQueryDevtools /> */}
    </QueryClientProvider>
  );
};

export default App;
