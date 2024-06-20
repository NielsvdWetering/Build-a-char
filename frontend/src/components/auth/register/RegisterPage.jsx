import { useAuthentication } from "../../../hooks";
import AuthPage from "../AuthPage";

export default function Register({ onRedirect }) {
  const { register } = useAuthentication();

  return (
    <AuthPage
      name="Register"
      onSubmit={register}
      redirectText="or log in if you already have an account"
      redirectURL="/login"
    />
  );
}
