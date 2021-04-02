import React from "react";
import {render, fireEvent} from "@testing-library/react";
import RegisterPage from "views/RegisterPage/RegisterPage.js";

it("renders correctly", () => {
    const {queryByTestId} = render(<RegisterPage/>);

    expect(queryByTestId("register-page")).toBeTruthy()
})