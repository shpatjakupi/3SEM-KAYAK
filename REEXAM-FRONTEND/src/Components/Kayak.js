import React, { useEffect } from "react";
import AddKayak from "./AddKayak";
import DeleteKayak from "./DeleteKayak";
import { Route } from "react-router-dom";
import Col from "react-bootstrap/Col";

const Kayak = props => {
  return (
    <Col>
      <div className="default-box">
        <div className="box-header"></div>

        <Route exact path="addKayak">
          <AddKayak
            facade={props.facade}
            allKayaks={props.allKayaks}
            allBookings={props.allBookings}
          />
        </Route>

        <Route exact path="deleteKayak">
          <DeleteKayak
            facade={props.facade}
            allKayaks={props.allKayaks}
            error={props.error}
            setError={props.setError}
            success={props.success}
            setSuccess={props.setSuccess}
            deletedKayak={props.deletedKayak}
            setDeletedKayak={props.setDeletedKayak}
          />
        </Route>
      </div>
    </Col>
  );
};
export default Kayak;
