import React from "react";
// nodejs library that concatenates classes
import classNames from "classnames";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// core components
import Header from "components/Header/Header.js";
import Footer from "components/Footer/Footer.js";
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import HeaderLinks from "components/Header/HeaderLinks.js";
import Parallax from "components/Parallax/Parallax.js";

import profile from "assets/img/faces/christian.jpg";

import studio1 from "assets/img/examples/studio-1.jpg";
import studio2 from "assets/img/examples/studio-2.jpg";
import studio4 from "assets/img/examples/studio-4.jpg";
import studio5 from "assets/img/examples/studio-5.jpg";

import styles from "assets/jss/material-kit-react/views/profilePage.js";
import axios from "axios";

const useStyles = makeStyles(styles);

export default function ProfilePage(props) {
  const classes = useStyles();
  const { ...rest } = props;
  const imageClasses = classNames(
    classes.imgRaised,
    classes.imgRoundedCircle,
    classes.imgFluid
  );
  const navImageClasses = classNames(classes.imgRounded, classes.imgGallery);
  const [images, setImages] = React.useState([]);
  const [username, setUserName] = React.useState('');
  const [isLoggedInUser, setIsLoggedInUser] = React.useState(false);

  React.useEffect(() => {
    // const username = localStorage.getItem('username');
    // const sessionId = localStorage.getItem('sessionId');
    // const sessionPayload = {
    //   "username": username,
    //   "sessionId" : sessionId
    // }

    // axios.post('http://localhost:8080/validate', sessionPayload)
    //   .then(function (response) {
    //     setIsLoggedInUser(response.data);
    // });

    //if (isLoggedInUser) {
    if(localStorage.getItem('sessionId') != null){

      setUserName(localStorage.getItem('username'));
      axios.get('http://localhost:8081/files?username=' + localStorage.getItem('username'))
        .then(function (response) {
          setImages(response.data);
        });
    }
    else {
      window.alert("You are not logged in. Please sign out and sign in to continue.");
      setTimeout(()=> props.history.push('/'), 2000);
    }
  }, [])

  return (
    <div>
      <Header
        color="transparent"
        brand="Swishh"
        rightLinks={<HeaderLinks />}
        fixed
        changeColorOnScroll={{
          height: 200,
          color: "white"
        }}
        {...rest}
      />
      <Parallax small filter image={require("assets/img/profile-bg.jpg")} />
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div>
          <div className={classes.container}>
            <GridContainer justify="center">
              <GridItem xs={12} sm={12} md={6}>
                <div className={classes.profile}>
                  <div>
                    <img src={profile} alt="..." className={imageClasses} />
                  </div>
                  <div className={classes.name}>
                    <h3 className={classes.title}>{username}</h3>
                  </div>
                </div>
              </GridItem>
            </GridContainer>
            <div className={classes.description}>
              <p>
                An artist of considerable range, Chet Faker — the name taken by
                Melbourne-raised, Brooklyn-based Nick Murphy — writes, performs
                and records all of his own music, giving it a warm, intimate
                feel with a solid groove structure.{" "}
              </p>
            </div>

            <GridContainer >
              <GridItem xs={12} sm={12} md={4}>
                {images.map((image) => (
                  <img
                    alt="..."
                    src={'data:image/jpeg;base64,' + image}
                    className={navImageClasses}
                  />
                ))}
                <img
                  alt="..."
                  src={studio1}
                  className={navImageClasses}
                />
                <img
                  alt="..."
                  src={studio2}
                  className={navImageClasses}
                />
              </GridItem>
              <GridItem xs={12} sm={12} md={4}>
                <img
                  alt="..."
                  src={studio5}
                  className={navImageClasses}
                />
                <img
                  alt="..."
                  src={studio4}
                  className={navImageClasses}
                />
              </GridItem>
              <GridItem xs={12} sm={12} md={4}>
                <img
                  alt="..."
                  src={studio2}
                  className={navImageClasses}
                />
                <img
                  alt="..."
                  src={studio1}
                  className={navImageClasses}
                />
              </GridItem>
            </GridContainer>
          </div>

        </div>
      </div>
      <Footer />
    </div>
  );
}
