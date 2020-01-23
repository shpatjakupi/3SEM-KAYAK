import React from "react";
import { Route, NavLink } from "react-router-dom";
import Col from "react-bootstrap/Col";
import Nav from "react-bootstrap/Nav";
import Kayak from "./Kayak";

const HomePageForAdmin = props => {
  return (
    <Col>
      <div className="default-box">
        <div className="box-header">ADMIN</div>
        <Nav className="flex-column">
          <Nav>
            <NavLink to="Kayak">Kayak</NavLink>
          </Nav>
        </Nav>
      </div>

      <Route path="addKayak">
        <Kayak />
      </Route>
    </Col>
  );
};
export default HomePageForAdmin;
