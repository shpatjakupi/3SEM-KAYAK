import React, { useState } from "react";

import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

function LogIn(props) {
  const [user, setUser] = useState({ username: "", password: "" });
  const login = evt => {
    evt.preventDefault();
    console.log("test");
    props.login(user.username, user.password);
  };
  const onChange = evt => {
    console.log(evt.target.id);
    user[evt.target.id] = evt.target.value;
    setUser({ ...user });
  };
  return (
    <Container>
      <Row className="d-flex justify-content-center">
        <Col md="5" className="default-box">
          <div className="box-header">Please enter your credentials</div>
          <Form onChange={onChange} className="text-center">
            <Form.Control placeholder="User Name" id="username" />
            <br />
            <Form.Control
              placeholder="Password"
              id="password"
              type="password"
            />
            <br />
            <Button onClick={login} className="w-100">
              Login
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
}
export default LogIn;
