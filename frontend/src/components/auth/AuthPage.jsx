import { useNavigate } from "react-router-dom";
import AuthForm from "./AuthForm";

export default function AuthPage({
  name,
  redirectText,
  redirectURL,
  onSubmit,
}) {
  const navigate = useNavigate();

  return (
    <AuthForm
      onSubmit={onSubmit}
      submitText={name}
      redirectText={redirectText}
      onRedirect={handleRedirect}
      onCompleted={() => navigate("/")}
    />
  );

  function handleRedirect() {
    if (!redirectURL) {
      console.error("redirectURL not found");
      return;
    }

    navigate(redirectURL);
  }
}
