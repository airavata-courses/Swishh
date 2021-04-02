import React, { useState, useCallback } from "react";
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
import GridList from '@material-ui/core/GridList';
import GridListTile from '@material-ui/core/GridListTile';
import GridListTileBar from '@material-ui/core/GridListTileBar';
import IconButton from '@material-ui/core/IconButton';
import ShareIcon from '@material-ui/icons/Share';
import GetAppIcon from '@material-ui/icons/GetApp';
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import { render } from "react-dom";
import Gallery from "react-photo-gallery";
import Carousel, { Modal, ModalGateway } from "react-images";
import profile from "assets/img/faces/christian.jpg";
import styles from "assets/jss/material-kit-react/views/profilePage.js";
import axios from "axios";
import Slide from "@material-ui/core/Slide";
import { photos } from "./photos";
import Foldericon from "assets/img/Folder-icon.png";
import Button from "components/CustomButtons/Button.js";
import Close from "@material-ui/icons/Close";
import CustomInput from "components/CustomInput/CustomInput.js";
import InputAdornment from "@material-ui/core/InputAdornment";
import Check from "@material-ui/icons/Check";
// core components
import SnackbarContent from "components/Snackbar/SnackbarContent.js";

const useStyles = makeStyles(styles);
const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="down" ref={ref} {...props} />;
});

export default function ProfilePage(props) {
  const classes = useStyles();
  const { ...rest } = props;
  const imageClasses = classNames(
    classes.imgRaised,
    classes.imgRoundedCircle,
    classes.imgFluid
  );
  const [currentImage, setCurrentImage] = useState(0);
  const [viewerIsOpen, setViewerIsOpen] = useState(false);
  const [showShare, setShowShare] = useState(false);
  const openLightbox = useCallback((event, { photo, index }) => {
    setCurrentImage(index);
    setViewerIsOpen(true);
  }, []);

  const closeLightbox = () => {
    setCurrentImage(0);
    setViewerIsOpen(false);
  };
  const navImageClasses = classNames(classes.imgRounded, classes.imgGallery);
  const [images, setImages] = React.useState([]);
  const [username, setUserName] = React.useState('');
  const [isLoggedInUser, setIsLoggedInUser] = React.useState(false);
  const [classicModal, setClassicModal] = React.useState(false);
  const download = (source, filename) => {
    console.log(source)
    var element = document.createElement("a");
    var file = new Blob(
      [
        filename
      ],
      { type: "image/*" }
    );
    element.href = URL.createObjectURL(file);
    element.download = filename;
    element.click();
    console.log("test")
  };
  const share = (fileid) => {
    var sharewith = ["test@gmail.com"];
    var fileids = [fileid];
    const payload={
      "username": username,
      "sharewith" : sharewith,
      "fileids" : fileids
    }
    axios.post('http://localhost:8080/share' , payload,{
    }).then(function (response) {
      setShowShare(true);
      setClassicModal(false)
      setTimeout(()=>  window.alert("Your image has been shared successfully"), 500);
    });
  }
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

    if (localStorage.getItem('sessionId') != null) {

      setUserName(localStorage.getItem('username'));
      axios.get('http://localhost:8080/files?username=' + localStorage.getItem('username'))
        .then(function (response) {
          console.log(response.data)
          setImages(response.data);
        });
    }
    else {
      window.alert("You are not logged in. Please sign out and sign in to continue.");
      setTimeout(() => props.history.push('/'), 2000);
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
              
            </div>
            
            {images.length === 0 ?

              <GridContainer >
                 <GridItem xs={12} sm={4} md={8} style= {{marginLeft : '28rem'}}>
                  There are no images to show
                  </GridItem>
              </GridContainer>
           :
            <GridContainer >
              <GridList cellHeight={250} cols={3}>
                {images.map((image) => (
                  <GridListTile key={image}>
                    <img src={'data:image/jpeg;base64,' + image.file} alt="..." />
                    <GridListTileBar
                      // title="Test"
                      // subtitle={<span>by: {tile.author}</span>}
                      actionIcon={
                        <IconButton aria-label={`info about test`} style={{color: 'rgba(255, 255, 255, 0.54)', }}>
                          <GetAppIcon style={{marginRight:'1rem'}}
                          onClick={() => {download(image.file, image.filename)}}/>
                          <ShareIcon
                          
                          onClick={() => setClassicModal(true)}/>
                          
                <Dialog
                  classes={{
                    root: classes.center,
                    paper: classes.modal
                  }}
                  open={classicModal}
                  TransitionComponent={Transition}
                  keepMounted
                  onClose={() => setClassicModal(false)}
                  aria-labelledby="classic-modal-slide-title"
                  aria-describedby="classic-modal-slide-description"
                >
                 
                  <DialogContent
                    id="classic-modal-slide-description"
                    className={classes.modalBody}
                  >
                    <CustomInput
                      labelText="Email..."
                      
                      formControlProps={{
                        fullWidth: true
                      }}
                      inputProps={{
                        type: "email",
                        endAdornment: (
                          <InputAdornment position="end">
                            <ShareIcon className={classes.inputIconsColor} />
                          </InputAdornment>
                        )
                      }}
                      
                    />
                  </DialogContent>
                  <DialogActions className={classes.modalFooter}>
                    <Button color="transparent" simple onClick={() => {share(image.filename)}}>
                      Share
                    </Button>
                    
                    <Button
                      onClick={() => setClassicModal(false)}
                      color="danger"
                      simple
                    >
                      Close
                    </Button>
                  </DialogActions>
                </Dialog>
                        </IconButton>
                      }
                    />
                  </GridListTile>
                  
                ))}
                
{/* 
                <GridListTile style={{ marginBottom: '6rem'}}>
                    <img src={Foldericon} alt="..." style={{ height : '285px', marginRight:'rem'}}/>
                    <GridListTileBar
                      title="Test"
                      // subtitle={<span>by: {tile.author}</span>}
                      actionIcon={
                        <IconButton aria-label={`info about test`} style={{color: 'rgba(255, 255, 255, 0.54)', }}>
                          <GetAppIcon style={{marginRight:'1rem'}}
                          />
                          <ShareIcon/>
                        </IconButton>
                      }
                    />
                  </GridListTile>
                  <GridListTile style={{ marginBottom: '6rem'}}>
                    <img src={Foldericon} alt="..." style={{ height : '285px', marginRight:'2rem'}}/>
                    <GridListTileBar
                      title="Test"
                      // subtitle={<span>by: {tile.author}</span>}
                      actionIcon={
                        <IconButton aria-label={`info about test`} style={{color: 'rgba(255, 255, 255, 0.54)', }}>
                          <GetAppIcon style={{marginRight:'1rem'}}
                          />
                          <ShareIcon/>
                        </IconButton>
                      }
                    />
                  </GridListTile>
                  <GridListTile style={{ marginBottom: '6rem'}}>
                    <img src={Foldericon} alt="..." style={{ height : '285px', marginRight:'2rem'}}/>
                    <GridListTileBar
                      title="Test"
                      // subtitle={<span>by: {tile.author}</span>}
                      actionIcon={
                        <IconButton aria-label={`info about test`} style={{color: 'rgba(255, 255, 255, 0.54)', }}>
                          <GetAppIcon style={{marginRight:'1rem'}}
                          />
                          <ShareIcon/>
                        </IconButton>
                      }
                    />
                  </GridListTile>
                  <GridListTile style={{ marginBottom: '6rem'}}>
                    <img src={Foldericon} alt="..." style={{ height : '285px', marginRight:'2rem'}}/>
                    <GridListTileBar
                      title="Test"
                      // subtitle={<span>by: {tile.author}</span>}
                      actionIcon={
                        <IconButton aria-label={`info about test`} style={{color: 'rgba(255, 255, 255, 0.54)', }}>
                          <GetAppIcon style={{marginRight:'1rem'}}
                          />
                          <ShareIcon/>
                        </IconButton>
                      }
                    />
                  </GridListTile>
                  <GridListTile style={{marginBottom: '6rem'}}>
                    <img src={Foldericon} alt="..." style={{ height : '285px', marginRight:'2rem'}}/>
                    <GridListTileBar
                      title="Test"
                      // subtitle={<span>by: {tile.author}</span>}
                      actionIcon={
                        <IconButton aria-label={`info about test`} style={{color: 'rgba(255, 255, 255, 0.54)', }}>
                          <GetAppIcon style={{marginRight:'1rem'}}
                          />
                          <ShareIcon/>
                        </IconButton>
                      }
                    />
                  </GridListTile> */}
                  
              </GridList>
            </GridContainer>
            }
          </div>

        </div>
      </div>
      <Footer />
    </div>
  );
}
