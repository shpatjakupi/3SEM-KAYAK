import React, { useState, useEffect } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Alert from "react-bootstrap/Alert";

const AddKayak = props => {
  const [kayak, setKayak] = useState({
    name: "",
    model: "",
    description: [{}],
    year: [{}],
    color: [{}],
    personsAllowed: [{}],
    images: [{}],
    bookingdates: [{}]
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);

  const onChange = evt => {
    const target = evt.target;
    if (target.tagName === "SELECT" && target.id !== "year") {
      const options = evt.target.options;
      let selected = [];
      for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
          selected.push({ name: options[i].value });
        }
      }
      kayak[target.id] = selected;
      setKayak({ ...kayak });
    } else {
      kayak[target.id] = target.value;
      setKayak({ ...kayak });
    }
  };

  const onClick = evt => {
    evt.preventDefault();
    if (
      kayak.name === "" ||
      kayak.model === "" ||
      kayak.description === "" ||
      kayak.year === "" ||
      kayak.color === "" ||
      kayak.personsAllowed === "" ||
      kayak.images.length === 0 ||
      kayak.bookingdates.length === 0
    ) {
      setError("You may not leave any fields empty!");
    } else {
      setError("");
      props.facade
        .addKayak(kayak)
        .then(res => {
          if (res.hasOwnProperty("id")) setSuccess(true);
        })
        .catch(err => console.log(err));
    }
  };

  return (
    <Form onChange={onChange}>
      <h5>Add Kayak</h5>

      {error !== "" ? <Alert variant="danger">{error}</Alert> : null}

      <Form.Group controlId="name">
        <Form.Label>What is the kayaks name: </Form.Label>
        <Form.Control type="text" placeholder="name" />
      </Form.Group>

      <Form.Group controlId="model">
        <Form.Label>what is the kayaks model: </Form.Label>
        <Form.Control type="text" placeholder="model" />
      </Form.Group>

      <Form.Group controlId="description">
        <Form.Label>description ? </Form.Label>
        <Form.Control type="text" placeholder="description" />
      </Form.Group>

      <Form.Group controlId="year">
        <Form.Label>what is the kayaks year? </Form.Label>
        <Form.Control as="select">
          <option>Select a year</option>
          {props.years.map((year, i) => (
            <option key={i}>{year}</option>
          ))}
        </Form.Control>
      </Form.Group>

      <Form.Group controlId="color">
        <Form.Label>what color do the kayak have ? </Form.Label>
        <Form.Control type="text" placeholder="color" />
      </Form.Group>

      <Form.Group controlId="personsAllowed">
        <Form.Label>how many persons are allowed on the kayak ? </Form.Label>
        <Form.Control type="text" placeholder="personsAllowed" />
      </Form.Group>

      <Form.Group controlId="images">
        <Form.Label>upload image url ? </Form.Label>
        <Form.Control type="text" placeholder="images" />
      </Form.Group>

      <Button onClick={onClick}>Add kayak</Button>
      {success ? (
        <Alert variant="success">The kayak has been added!</Alert>
      ) : null}
    </Form>
  );
};

export default AddKayak;
