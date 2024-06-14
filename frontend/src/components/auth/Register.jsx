import { useState } from "react";
import AuthForm from "./AuthForm";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function Register() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  return (
    <AuthForm
      username={username}
      setUsername={setUsername}
      password={password}
      setPassword={setPassword}
      onSubmit={handleSubmit}
      submitTitle="Register"
    />
  );

  function handleSubmit(registerData) {
    axios
      .post("http://localhost:8080/api/v1/auth/register", registerData)
      .then((response) => navigate("/"))
      .catch(console.error);
  }
}
