/*eslint-disable*/
import React from "react";
import axios from 'axios';
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import Slide from "@material-ui/core/Slide";
import ListItem from "@material-ui/core/ListItem";
import Tooltip from "@material-ui/core/Tooltip";
// @material-ui/icons
import PublishIcon from '@material-ui/icons/Publish';
import Close from "@material-ui/icons/Close";

// core components
import CustomDropdown from "components/CustomDropdown/CustomDropdown.js";
import Button from "components/CustomButtons/Button.js";

import styles from "assets/jss/material-kit-react/components/headerLinksStyle.js";

const useStyles = makeStyles(styles);

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="down" ref={ref} {...props} />;
});

Transition.displayName = "Transition";
export default function HeaderLinks(props) {
  const classes = useStyles();

    const hiddenFileInput = React.useRef(null);
    const handleClick = () => {
      console.log("signed out");
      console.log("Invalidated token successfully!");
      localStorage.clear();
      window.location.href = '/';
    };
    const handleChange = event => {
      const fileUploaded = event.target.files[0];
      const username = localStorage.getItem(username);
      console.log(fileUploaded);

      let formData = new FormData();
      formData.append("file", fileUploaded);
      formData.append('username',username);

      axios.post('http://localhost:8080/upload/', formData ,{
        headers: {
          'Content-Type': 'multipart/form-data',
        }
      }).then(function (response) {
        console.log(response);
      });
    };

  return (
    <List className={classes.list}>
      <ListItem className={classes.listItem}>
        <CustomDropdown
          left
          hoverColor="info"
          dropdownHeader="Dropdown Header"
          buttonIcon="settings"
          buttonProps={{
            className: classes.navLink,
            color: "transparent"
          }}
          dropdownList={[
            
            <a href='/profile-page'>Profile</a>,
            <a href='/profile-page'>Contact us</a>,
            <a href='/profile-page'>Settings</a>,
            { divider: true },
            <a onClick={() => {handleClick()}}>Sign out</a>,
          ]}
        />
      </ListItem>
      <ListItem className={classes.listItem}>
        <Tooltip
          title="Upload an image"
          placement={window.innerWidth > 959 ? "top" : "left"}
          classes={{ tooltip: classes.tooltip }}
        >
          <Button
            target="_blank"
            color="transparent"
            className={classes.navLink}
            variant="contained"
            component="label"
          >
            
            <PublishIcon fontSize="large" />
            <input
              // type="file"
              hidden
              type="file"
              ref={hiddenFileInput}
              onChange={handleChange}
              // style={{display: 'none'}}
            />
          </Button>
        </Tooltip>

      </ListItem>
    </List>
  );
}
