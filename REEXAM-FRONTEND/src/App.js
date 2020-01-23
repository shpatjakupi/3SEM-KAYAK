import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Route,
  Switch,
  NavLink,
  Redirect
} from "react-router-dom";
import "./App.css";
import facade from "./ApiFacade";
import LogIn from "./Components/LogIn";
import HomePage from "./Components/HomePage";
import HomePageForAdmin from "./Components/HomePageForAdmin";

import Kayak from "./Components/Kayak";

import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";

function App() {
  const [loggedIn, setLoggedIn] = useState(false);
  const [allKayaks, setAllKayaks] = useState([{}]);
  const [allBookings, setAllBookings] = useState([{}]);
  const [years, setYears] = useState([]);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);

  const login = (user, pass) => {
    facade
      .login(user, pass)
      .then(res => setLoggedIn(true))
      .catch(err => console.log("wrong credentials"));
  };
  const logout = () => {
    facade.logout();
    setLoggedIn(false);
  };

  useEffect(() => {
    if (facade.getRole() === "admin" || facade.getRole === "user")
      setLoggedIn(true);
    let startYear = 2020;
    for (let i = 0; i < 2021 - 1960; i++) {
      years[i] = startYear;
      startYear--;
    }
    setYears([...years]);
    facade
      .fetchAllKayaks()
      .then(res => setAllKayaks([...res]))
      .catch(err => console.log(err));

    facade
      .fetchAllBookings()
      .then(res => setAllBookings([...res]))
      .catch(err => console.log(err));
  }, []);

  return (
    <div>
      <Router>
        <Header loggedIn={loggedIn} logout={logout} facade={facade} />
        <Switch>
          <Route exact path="/">
            <HomePage loggedIn={loggedIn} facade={facade} />
          </Route>
          <Route path="/login">
            {!loggedIn ? <LogIn login={login} /> : <Redirect to="/" />}
          </Route>

          <Route path="/admin">
            <Container>
              <Row className="d-flex justify-content-center">
                <HomePageForAdmin />
                <Route path="/Components">
                  <Kayak
                    facade={facade}
                    allKayaks={allKayaks}
                    allBookings={allBookings}
                    error={error}
                    setError={setError}
                    success={success}
                    setSuccess={setSuccess}
                  />
                </Route>
              </Row>
            </Container>
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

const Header = props => {
  return (
    <ul className="menu">
      <li>
        <NavLink to="/" exact activeClassName="active">
          HomePage
        </NavLink>
      </li>
      {props.loggedIn && props.facade.getRole() === "admin" ? (
        <li>
          <NavLink to="/admin" activeClassName="active">
            Admin
          </NavLink>
        </li>
      ) : null}

      {props.loggedIn ? (
        <li>
          <NavLink to="/login" activeClassName="active" onClick={props.logout}>
            Logout
          </NavLink>
        </li>
      ) : (
        <li>
          <NavLink to="/login" activeClassName="active">
            Login
          </NavLink>
        </li>
      )}
    </ul>
  );
};

export default App;
