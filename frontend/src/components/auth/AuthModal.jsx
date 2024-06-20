import { useState } from "react";
import AuthForm from "./AuthForm";
import { useAuthentication } from "../../hooks";

export default function AuthModal({ id, onCompleted }) {
  const [flag, setFlag] = useState(true);
  const { login, register } = useAuthentication();

  return (
    <dialog className="modal" id={id}>
      <div className="modal-box">
        {flag ? (
          <AuthForm
            onSubmit={login}
            submitText="Login"
            redirectText="or register if you do not have an account"
            onRedirect={switchDialog}
            onCompleted={onCompleted}
          />
        ) : (
          <AuthForm
            onSubmit={register}
            submitText="Register"
            redirectText="or login if you already have an account"
            onRedirect={switchDialog}
            onCompleted={onCompleted}
          />
        )}
      </div>
    </dialog>
  );

  function switchDialog() {
    setFlag((flag) => !flag);
  }
}
