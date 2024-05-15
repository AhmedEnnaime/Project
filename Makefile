run:
	@docker compose up -d
down:
	@docker compose down
test:
	@cd backend && mvn test

clean:
	@cd backend && mvn clean install