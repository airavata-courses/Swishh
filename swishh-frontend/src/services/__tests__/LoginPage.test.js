import React from "react";
import {render, fireEvent} from "@testing-library/react";
import LoginPage from "views/LoginPage/LoginPage.js";

it("renders correctly", () => {
    const {queryByTestId} = render(<LoginPage/>);

    expect(queryByTestId("login-page")).toBeTruthy()
})