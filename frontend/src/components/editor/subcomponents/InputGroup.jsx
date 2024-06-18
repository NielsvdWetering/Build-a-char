export default function InputGroup({ children, title }) {
  return (
    <div className="card card-body glass bg-primary shadow-lg">
      {title && <div className="card-title">{title}</div>}
      {children}
    </div>
  );
}
