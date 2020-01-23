import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Alert from "react-bootstrap/Alert";

const DeleteKayak = props => {
  const [kayak, setKayak] = useState("");

  const onChange = evt => {
    setKayak(evt.target.value);
  };

  const onClick = evt => {
    evt.preventDefault();
    console.log(kayak);
    if (kayak === "") {
      props.setError("You must choose a kayak !");
    } else {
      props.setError("");
      props.facade
        .deleteKayak(kayak)
        .then(res => {
          if (res.hasOwnProperty("id")) props.setSuccess(true);
        })
        .catch(err => console.log(err));
    }
  };

  return (
    <Form onChange={onChange}>
      <h5>Delete movie</h5>

      {props.error !== "" ? (
        <Alert variant="danger">{props.error}</Alert>
      ) : null}

      <Form.Group controlId="year">
        <Form.Label>What kayak would you like to delete?</Form.Label>
        <Form.Control as="select">
          <option>Select a kayak</option>
          {props.allKayak.map((kayak, i) => (
            <option key={i} value={kayak.id}>
              {kayak.id}
            </option>
          ))}
        </Form.Control>
      </Form.Group>

      <Button onClick={onClick}>Add kayak</Button>
      {props.success ? (
        <Alert variant="success">The kayak has been deleted!</Alert>
      ) : null}
    </Form>
  );
};

export default DeleteKayak;
