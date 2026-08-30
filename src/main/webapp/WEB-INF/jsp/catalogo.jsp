<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${titulo}</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary-navy: #0f172a;
            --accent-gold: #d97706;
            --accent-gold-hover: #b45309;
            --bg-light: #f8fafc;
            --card-border: #e2e8f0;
        }
        body {
            font-family: 'Plus Jakarta Sans', sans-serif;
            background-color: var(--bg-light);
            color: #334155;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .navbar-custom { background-color: rgba(15, 23, 42, 0.98); backdrop-filter: blur(10px); }
        .btn-gold {
            background-color: var(--accent-gold);
            color: #ffffff;
            font-weight: 600;
            border-radius: 10px;
            padding: 10px 22px;
            border: none;
            transition: all 0.25s ease;
        }
        .btn-gold:hover {
            background-color: var(--accent-gold-hover);
            color: #ffffff;
            transform: translateY(-2px);
        }
        .card-custom {
            background: #ffffff;
            border-radius: 16px;
            border: 1px solid var(--card-border);
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05);
        }
        .room-card { transition: all 0.25s ease; }
        .room-card:hover {
            transform: translateY(-3px);
            box-shadow: 0 12px 24px -5px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-dark navbar-custom sticky-top py-2">
        <div class="container">
            <a class="navbar-brand d-flex align-items-center gap-2" href="${pageContext.request.contextPath}/home">
                <i class="bi bi-building-fill-check text-warning fs-3"></i>
                <div>
                    <span class="fs-5 fw-bold text-white">GRAND ROYAL</span>
                    <small class="d-block text-warning" style="font-size: 0.65rem; letter-spacing: 2px;">HOTELS & RESORTS (JSP)</small>
                </div>
            </a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mx-auto mb-2 mb-lg-0 fw-medium gap-lg-2">
                    <li class="nav-item"><a class="nav-link text-light" href="${pageContext.request.contextPath}/home"><i class="bi bi-house-door me-1"></i>Inicio</a></li>
                    <li class="nav-item"><a class="nav-link active text-warning" href="${pageContext.request.contextPath}/catalogo"><i class="bi bi-door-open me-1"></i>Habitaciones</a></li>
                </ul>
                <a href="${pageContext.request.contextPath}/home" class="btn btn-sm btn-outline-light rounded-pill px-3"><i class="bi bi-arrow-left me-1"></i>Nueva Búsqueda</a>
            </div>
        </div>
    </nav>

    <!-- Resumen Dinámico inyectado desde Java JSP -->
    <div class="container py-4">
        <div class="card-custom p-3 mb-4 bg-white border-start border-4 border-warning">
            <div class="d-flex flex-wrap justify-content-between align-items-center gap-2">
                <div>
                    <small class="text-muted d-block fw-semibold">RESULTADOS PROCESADOS POR JAVA JSP:</small>
                    <h5 class="fw-bold text-dark mb-0">
                        <c:choose>
                            <c:when test="${not empty sucursal}">
                                <c:out value="${sucursal.nombre}"/>
                            </c:when>
                            <c:otherwise>
                                Hotel Royal San Salvador
                            </c:otherwise>
                        </c:choose>
                        &bull; 
                        <span class="text-primary">${checkIn}</span> al 
                        <span class="text-primary">${checkOut}</span> 
                        (${totalNoches} Noches, ${huespedes} Huéspedes)
                    </h5>
                </div>
                <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-dark btn-sm rounded-pill px-3">
                    <i class="bi bi-pencil-square me-1"></i>Modificar Fechas
                </a>
            </div>
        </div>

        <div class="row g-4">
            <!-- Sidebar de Filtros -->
            <div class="col-lg-3">
                <div class="card-custom p-4 sticky-top" style="top: 80px;">
                    <h6 class="fw-bold mb-3 border-bottom pb-2"><i class="bi bi-funnel-fill text-warning me-1"></i>Filtros de Catálogo</h6>
                    
                    <form action="${pageContext.request.contextPath}/catalogo" method="get">
                        <input type="hidden" name="checkIn" value="${checkIn}" />
                        <input type="hidden" name="checkOut" value="${checkOut}" />
                        <input type="hidden" name="huespedes" value="${huespedes}" />

                        <div class="mb-3">
                            <label class="form-label fw-semibold small">Cambiar Sucursal:</label>
                            <select class="form-select form-select-sm" name="sucursalId" onchange="this.form.submit()">
                                <option value="1" ${sucursalId == 1 ? 'selected' : ''}>Hotel Royal San Salvador (5★)</option>
                                <option value="2" ${sucursalId == 2 ? 'selected' : ''}>Resort Costa del Sol (4★)</option>
                            </select>
                        </div>
                    </form>

                    <div class="mb-3">
                        <label class="form-label fw-semibold small">Total Habitaciones:</label>
                        <div class="p-2 bg-light rounded text-center fw-bold text-primary fs-5">${totalResultados} Disponibles</div>
                    </div>
                </div>
            </div>

            <!-- Listado de Habitaciones renderizado por JSP con c:forEach -->
            <div class="col-lg-9">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h5 class="fw-bold mb-0">Habitaciones Disponibles (${totalResultados})</h5>
                </div>

                <c:forEach items="${habitaciones}" var="hab">
                    <div class="card-custom room-card mb-4 overflow-hidden">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img src="https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=600&q=80" 
                                     class="img-fluid h-100 object-fit-cover w-100" style="min-height: 200px;" alt="Habitacion" />
                            </div>
                            <div class="col-md-8 p-4 d-flex flex-column justify-content-between">
                                <div>
                                    <div class="d-flex justify-content-between align-items-start">
                                        <div>
                                            <span class="badge bg-success fw-semibold mb-1">${hab.estado}</span>
                                            <h4 class="fw-bold mb-1">${hab.tipoHabitacion.nombreTipo} #${hab.numeroHabitacion}</h4>
                                            <p class="text-muted small mb-2">
                                                <i class="bi bi-geo-alt me-1"></i>Piso ${hab.piso} &bull; 
                                                ${hab.descripcionVista}
                                            </p>
                                        </div>
                                        <div class="text-end">
                                            <div class="fs-4 fw-bold text-dark">
                                                $<fmt:formatNumber value="${hab.precioNoche}" type="number" minFractionDigits="2" maxFractionDigits="2"/> 
                                                <span class="fs-6 text-muted fw-normal">/ noche</span>
                                            </div>
                                            <small class="text-muted">Estancia (${totalNoches} noches): 
                                                <strong>$<fmt:formatNumber value="${hab.precioNoche * totalNoches}" type="number" minFractionDigits="2" maxFractionDigits="2"/></strong>
                                            </small>
                                        </div>
                                    </div>
                                    <p class="small text-muted mb-2">${hab.tipoHabitacion.descripcion}</p>
                                    <div class="d-flex flex-wrap gap-3 text-muted small my-2">
                                        <span><i class="bi bi-people me-1 text-primary"></i>Capacidad: ${hab.tipoHabitacion.capacidadAdultos} Adultos</span>
                                        <span><i class="bi bi-wifi me-1 text-primary"></i>WiFi 500Mbps</span>
                                        <span><i class="bi bi-snow me-1 text-primary"></i>Aire Acondicionado</span>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-between align-items-center pt-3 border-top mt-2">
                                    <span class="text-success small fw-semibold"><i class="bi bi-check-circle-fill me-1"></i>Cancelación gratuita</span>
                                    <button class="btn btn-gold px-4">
                                        Seleccionar Habitación <i class="bi bi-arrow-right ms-1"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <!-- Mensaje si la lista JSP está vacía -->
                <c:if test="${empty habitaciones}">
                    <div class="card-custom p-5 text-center">
                        <i class="bi bi-info-circle fs-1 text-warning mb-2"></i>
                        <h5 class="fw-bold">No hay habitaciones registradas en la base de datos para esta sucursal</h5>
                        <p class="text-muted small">Al arrancar el servidor Java, se cargarán automáticamente los datos de prueba.</p>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white py-4 mt-auto">
        <div class="container text-center small text-secondary">
            <strong>Grand Royal Hotels & Resorts</strong> &bull; JavaServer Pages (JSP) &bull; DWF 2026 &bull; Universidad Don Bosco
        </div>
    </footer>
</body>
</html>
