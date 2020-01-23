import React, { useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import Button from "react-bootstrap/Button";

const HomePage = props => {
  const facade = props.facade;
  const [search, setSearch] = useState({ query: "", searchBy: "" });
  const [searchResults, setSearchResults] = useState([
    { bookingdates: [], images: [] }
  ]);
  const onChange = evt => {
    const target = evt.target;
    if (target.tagName === "SELECT") {
      search["searchBy"] = target.value;
      setSearch({ ...search });
      switch (target.value) {
        case "getbyspace":
          document.getElementById("searchInput").placeholder =
            "enter a valid number";
          break;
        default:
          break;
      }
    } else if (target.tagName === "INPUT") {
      search["query"] = target.value;
      setSearch({ ...search });
    }
  };

  const onClick = evt => {
    evt.preventDefault();
    if (search.query === "" || search.searchBy === "") {
      document.getElementById("search-results").innerText =
        "You may not leave any fields empty.";
    } else {
      facade
        .fetchKayak(search)
        .then(res => {
          setSearchResults([...res]);
          console.log(res);
        })
        .catch(err => {
          console.log(err);
          setSearchResults([{ images: [], bookingdates: [] }]);
        });
    }
  };

  return (
    <Container>
      <Row className="d-flex justify-content-center">
        <Col className="d-flex default-box">
          <div className="box-header">Home</div>
          <div>
            <p>Search for a kayak</p>
            <Form onChange={onChange}>
              <InputGroup className="mb-3">
                <Form.Control
                  id="searchInput"
                  placeholder="enter a valid number"
                  aria-label="search-input"
                />
                <InputGroup.Append>
                  <Form.Control as="select">
                    <option value="">Sort by:</option>
                    <option value="getbyspace">persons allowed</option>
                  </Form.Control>
                </InputGroup.Append>
              </InputGroup>
              <Button onClick={onClick}>Search for a kayak</Button>
            </Form>
          </div>
          <div className="search-results-box" id="search-results">
            {searchResults[0].name !== "" ? (
              <ul className="kayak-list">
                {searchResults.map((kayak, i) => (
                  <li key={i} className="kayak-box">
                    <div>
                      <label>Name:</label>
                      {kayak.name}
                    </div>

                    <div>
                      <label>Model:</label>
                      {kayak.Model}
                    </div>

                    <div>
                      <label>description:</label>
                      {kayak.description}
                    </div>
                    <div>
                      <label>year:</label>
                      {kayak.year}
                    </div>
                    <div>
                      <label>color:</label>
                      {kayak.color}
                    </div>
                    <div>
                      <label>personsAllowed:</label>
                      {kayak.personsAllowed}
                    </div>

                    <div>
                      <label>images:</label>
                      <ul>
                        {kayak.images.map((images, j) => (
                          <li key={j}>
                            {" "}
                            <img
                              height="100"
                              width="100"
                              border="1"
                              src={images.url}
                            />
                          </li>
                        ))}
                      </ul>
                    </div>

                    <div>
                      <label>bookingdates:</label>
                      <ul>
                        {kayak.bookingdates.map((bookingdates, ij) => (
                          <li key={ij}> {bookingdates.bookingDate}</li>
                        ))}
                      </ul>
                    </div>
                  </li>
                ))}
              </ul>
            ) : (
              <span>No results</span>
            )}
          </div>
        </Col>
      </Row>
    </Container>
  );
};

export default HomePage;
