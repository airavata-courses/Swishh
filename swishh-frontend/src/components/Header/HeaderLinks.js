/*eslint-disable*/
import React from "react";
import DeleteIcon from "@material-ui/icons/Delete";
import IconButton from "@material-ui/core/IconButton";
// react components for routing our app without refresh
import { Link } from "react-router-dom";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import Slide from "@material-ui/core/Slide";
import ListItem from "@material-ui/core/ListItem";
import Tooltip from "@material-ui/core/Tooltip";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import Typography from '@material-ui/core/Typography';


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
  const [classicModal, setClassicModal] = React.useState(false);
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
            <a href='/'>Sign out</a>,
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
              type="file"
              hidden
            />
          </Button>
        </Tooltip>

      </ListItem>
    </List>
  );
}
