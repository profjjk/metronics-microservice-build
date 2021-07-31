import { useState } from "react";
import API from "../../API";
import Container from "../Container/Container";
import Header from "../Header/index";
import "./style.css";
import { useHistory } from "react-router-dom";
import SideNavbar from "../SideNavbar";


function LoginForm() {
  let history = useHistory();
  const [state, setState] = useState({
    email: "",
    password: "",
    // confirmPassword: "",
    successMessage: "",
  });
  const handleChange = (e) => {
    const { id, value } = e.target;
    setState((prevState) => ({
      ...prevState,
      [id]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    console.log(state.password + state.email);
    API.loggedIn(state.email, state.password)
    .then(
      history.push("/home"))
  };

  return (
    <>
    <Container>
      <div className="card Login col-12 col-lg-4 login-card d-flex align-items-center ">
        <span> Login </span>
       <form>
          <div className="form-group text-left">
            <label htmlFor="exampleInputEmail1">Email address</label>
            <input
              type="email"
              className="form-control"
              id="email"
              aria-describedby="emailHelp"
              placeholder="Enter email"
              value={state.email}
              onChange={handleChange}
            />
            <small id="emailHelp" className="form-text text-muted">
              We'll never share your email with anyone else.
            </small>
          </div>
          <div className="form-group text-left">
            <label htmlFor="exampleInputPassword1">Password</label>
            <input
              type="password"
              className="form-control"
              id="password"
              placeholder="Password"
              value={state.password}
              onChange={handleChange}
            />
          </div>
          {/* <div className="form-group text-left">
                <label htmlFor="exampleInputPassword1">Confirm Password</label>
                <input type="password" 
                    className="form-control" 
                    id="confirmPassword" 
                    placeholder="Confirm Password"
                    value={state.confirmPassword}
                    onChange={handleChange} 
                />
            </div> */}
          <button
            type="submit"
            className="btn btn-primary"
            onClick={handleSubmit}
          >
            Submit
          </button>
        </form>
        <div
          className="alert alert-success mt-2"
          style={{ display: state.successMessage ? "block" : "none" }}
          role="alert"
        >
          {state.successMessage}
        </div>
        <div className="mt-2">
          <span>Don't have an account? </span>
          {/* <span className="loginText" onClick={() => redirectToLogin()}>Login here</span>  */}
        </div>
      </div>
    </Container>
    </>
  );
}

export default LoginForm;
