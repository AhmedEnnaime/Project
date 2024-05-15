run:
	@docker compose up -d
down:
	@docker compose down
test:
	@cd backend && mvn verify

clean:
	@cd backend && mvn clean install